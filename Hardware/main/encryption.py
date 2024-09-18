import json
import rsa
import base64
from datetime import datetime, timedelta

data = {
    "id": 0,
    "expire_date": (datetime.utcnow() + timedelta(minutes=1)).isoformat()
}

with open("keys/public.pem", "rb") as file:
    public_key = rsa.PublicKey.load_pkcs1(file.read())

json_data = json.dumps(data)

encrypted_message = rsa.encrypt(json_data.encode('utf-8'), public_key)

print(encrypted_message)

open("encrypted_message.txt", "wb").write(base64.b64encode(encrypted_message))
