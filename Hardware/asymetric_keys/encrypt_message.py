import rsa

with open("keys/public.pem", "rb") as file:
    public_key = rsa.PublicKey.load_pkcs1(file.read())

with open("../jwt/encoded_message.txt", "rb") as message_file:
    message = message_file.read()

encrypted_message = rsa.encrypt(message, public_key)

with open("jwt_encrypted.message", "wb") as encrypted_message_file:
    encrypted_message_file.write(encrypted_message)
