import datetime

import rsa
import jwt
import base64

id = 0
algorithm = "RS256"
payload = {'user_id': 1, 'exp': datetime.datetime.utcnow() + datetime.timedelta(hours=1)}

with open("../asymetric_keys/keys/public.pem", "rb") as file:
    public_key = rsa.PublicKey.load_pkcs1(file.read())

encoded = jwt.encode(payload, public_key, algorithm=algorithm)
print(encoded)

open("encrypted_message.txt", "wb").write(base64.b64encode(encoded))
