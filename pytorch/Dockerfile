FROM pytorch/pytorch:latest

WORKDIR /app

RUN pip install numpy pandas

COPY sources .

CMD ["python", "main.py"]