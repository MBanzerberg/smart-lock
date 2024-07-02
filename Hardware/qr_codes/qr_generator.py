import segno

with open('C:/Users/barte/Desktop/Inzynierka/SmartLock/Hardware/main/encrypted_message.txt', 'rb') as file:
    my_file = file.read()

qrcode = segno.make_qr(my_file)
qrcode.save("my_hello_qr.png",
            scale=5)
            #border=1,
            #light="lightblue",
            #dark="darkblue",
            #data_dark="red")


