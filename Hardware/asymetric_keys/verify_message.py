import rsa
from rsa import VerificationError

with open("keys/public.pem", "rb") as public_file:
    public_key = rsa.PublicKey.load_pkcs1(public_file.read())

with open("message.txt", "rb") as message_file:
    message = message_file.read()

with open("signature.txt", "rb") as signature_file:
    signature = signature_file.read()

try:
    is_verified = rsa.verify(message, signature, public_key)
    print(is_verified)
except VerificationError:
    print("Wiadomość niezweryfikowana!")