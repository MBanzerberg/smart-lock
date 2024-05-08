import rsa
import jwt

id = 0
algorithm = "HS256"
secret = "bmil"

with open("../asymetric_keys/keys/public.pem", "rb") as file:
    public_key = rsa.PublicKey.load_pkcs1(file.read())

with open("message.txt", "rb") as message_file:
    message = message_file.read()

encrypted_message = rsa.encrypt(message, public_key)
#print(encrypted_message)

encoded = jwt.encode({id: str(encrypted_message)}, secret, algorithm=algorithm)
print(encoded)

open("encrypted_message.txt", "w").write(encoded)
