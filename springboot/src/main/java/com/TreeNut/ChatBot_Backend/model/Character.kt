package com.TreeNut.ChatBot_Backend.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "characters")
data class Character(
    @Id @Column(name = "idx", columnDefinition = "CHAR(36)")
    val idx: Long? = null, // UUID로 자동 생성되는 ID 필드
    val uuid : String,
    val useridx: Int, // 사용자의 ID
    val character_name: String, // 캐릭터 이름
    val description : String, //캐릭터 한줄 설명
    val greetings : String, //첫 인사말
    val image : String, //이미지 링크
    val character_setting : String, //캐릭터 설정
    val accessLevel : String, //접근 가능 여부
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(), // 생성 일시
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now() // 수정 일시
) {
    // 추가적인 메서드나 로직이 필요하면 여기에 작성
    @PreUpdate
    fun onUpdate() {
    updatedAt = LocalDateTime.now() // 수정 시점 업데이트
    }
}
