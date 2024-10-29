import json
import rsa
import base64
from datetime import datetime, timedelta

def encrypt_data(public_key_str):
    # Wczytaj klucz publiczny z podanego łańcucha
    public_key = rsa.PublicKey.load_pkcs1(public_key_str.encode('utf-8'))

    data = {
        "id": 0,
        "expire_date": (datetime.utcnow() + timedelta(minutes=1)).isoformat()
    }

    json_data = json.dumps(data)

    # Szyfruj wiadomość
    encrypted_message = rsa.encrypt(json_data.encode('utf-8'), public_key)

    # Zwróć zaszyfrowaną wiadomość jako string w formacie base64
    return base64.b64encode(encrypted_message).decode('utf-8')
