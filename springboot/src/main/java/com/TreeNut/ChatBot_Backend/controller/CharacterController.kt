package com.TreeNut.ChatBot_Backend.controller

import com.TreeNut.ChatBot_Backend.model.Character
import com.TreeNut.ChatBot_Backend.service.CharacterService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID
import com.TreeNut.ChatBot_Backend.middleware.TokenAuth
import javax.servlet.http.HttpServletRequest
import java.sql.SQLException
import org.springframework.http.HttpStatus
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import java.time.LocalDateTime


@RestController
@RequestMapping("/server/character")
class CharacterController(
    private val characterService: CharacterService,
    private val tokenAuth: TokenAuth,
    @Value("\${jwt.secret}") private val jwtSecret: String
) {

    @PostMapping("/add")
    fun addCharacter(
        @RequestBody body: Map<String, Any>,
        @RequestHeader("Authorization") authorization: String?
    ): ResponseEntity<Map<String, Any>> {
        // 토큰 확인
        val token = authorization?.substringAfter("Bearer ") 
            ?: return ResponseEntity.badRequest().body(mapOf("status" to 401, "message" to "토큰 없음"))
    
        // JWT에서 사용자 ID 추출
        val userid = tokenAuth.authGuard(token)
            ?: return ResponseEntity.badRequest().body(mapOf("status" to 401, "message" to "유효한 토큰이 필요합니다."))
    
        // 요청 본문에서 캐릭터 속성 추출
        val characterName = body["character_name"] as? String
            ?: return ResponseEntity.badRequest().body(mapOf("status" to 400, "message" to "Character name is required"))
        val description = body["description"] as? String
            ?: return ResponseEntity.badRequest().body(mapOf("status" to 400, "message" to "Description is required"))
        val greeting = body["greeting"] as? String
            ?: return ResponseEntity.badRequest().body(mapOf("status" to 400, "message" to "Greeting is required"))
        val image = body["image"] as? String
            ?: return ResponseEntity.badRequest().body(mapOf("status" to 400, "message" to "Image is required"))
        val characterSetting = body["character_setting"] as? String
            ?: return ResponseEntity.badRequest().body(mapOf("status" to 400, "message" to "Character setting is required"))
        val accessLevel = body["accessLevel"] as? Boolean
            ?: return ResponseEntity.badRequest().body(mapOf("status" to 400, "message" to "Access level is required"))
    
        // 캐릭터 객체 생성
        val newCharacter = Character(
            uuid = UUID.randomUUID().toString().replace("-", ""),
            userid = userid,
            characterName = characterName,
            description = description,
            greeting = greeting,
            image = image,
            characterSetting = characterSetting,
            accessLevel = accessLevel,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
    
        return try {
            val registeredCharacter = characterService.addCharacter(newCharacter)
            ResponseEntity.ok(mapOf("status" to 200, "name" to registeredCharacter.characterName))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf("status" to 500, "message" to "Error during character addition"))
        }
    }


    @PutMapping("/edit")
    fun editCharacter(
        @RequestParam characterName: String, // 수정하려는 캐릭터 이름을 받음
        @RequestBody updatedCharacter: Character, // 수정할 캐릭터 정보
        @RequestHeader("Authorization") userToken: String // JWT 토큰
    ): ResponseEntity<Any> {
        return try {
            // 1. JWT 토큰에서 사용자 ID 추출
            val tokenUserId = tokenAuth.authGuard(userToken)
                ?: return ResponseEntity.badRequest().body(mapOf("status" to 400, "message" to "유효한 사용자 ID가 필요합니다."))

            // 2. 기존 캐릭터 찾기 (캐릭터 이름으로)
            val character = characterService.getCharacterByName(characterName).firstOrNull()
                ?: return ResponseEntity.badRequest().body(mapOf("status" to 404, "message" to "캐릭터를 찾을 수 없습니다."))

            // 3. 권한 확인: 캐릭터의 소유자가 맞는지 검증
            if (character.userid != tokenUserId) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(mapOf("status" to 403, "message" to "이 캐릭터를 수정할 권한이 없습니다."))
            }

            // 4. 업데이트할 캐릭터 정보 생성
            val editedCharacterEntity = character.copy(
                characterName = updatedCharacter.characterName ?: character.characterName,
                description = updatedCharacter.description ?: character.description,
                greeting = updatedCharacter.greeting ?: character.greeting,
                image = updatedCharacter.image ?: character.image,
                characterSetting = updatedCharacter.characterSetting ?: character.characterSetting,
                accessLevel = updatedCharacter.accessLevel ?: character.accessLevel,
                updatedAt = LocalDateTime.now() // 수정 시간 업데이트
            )

            // 5. 캐릭터 수정 처리
            characterService.editCharacter(characterName, editedCharacterEntity, userToken)

            // 6. 응답 반환
            ResponseEntity.ok(mapOf("status" to 200, "message" to "캐릭터가 성공적으로 수정되었습니다."))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf("status" to 500, "message" to "캐릭터 수정 중 오류 발생: ${e.message}"))
        }
    }
}
/* 
    @DeleteMapping("/delete")
    fun deleteCharacter(@RequestBody body: Map<String, String>): ResponseEntity<Map<String, Any>> {
        // 구현 예정
    }
    
}*/