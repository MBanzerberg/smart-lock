import rsa


with open("keys/private.pem", "rb") as key_file:
    private_key = rsa.PrivateKey.load_pkcs1(key_file.read())

with open("message.txt", "rb") as message_file:
    message = message_file.read()

signature = rsa.sign(message, private_key, "SHA-256")

with open("signature.txt", "wb") as signature_file:
    signature_file.write(signature)