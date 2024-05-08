import rsa

with open('keys/private.pem', 'rb') as key_file:
    private_key = rsa.PrivateKey.load_pkcs1(key_file.read())

with open('jwt_encrypted.message', 'rb') as file:
    encrypted_message = file.read()

decrypted_message = rsa.decrypt(encrypted_message, private_key)

open("decrypted_message.txt", "wb").write(decrypted_message)

print(decrypted_message.decode())