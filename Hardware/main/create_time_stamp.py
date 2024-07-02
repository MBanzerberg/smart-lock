from datetime import datetime

current = datetime.now()

open("message.txt", "w").write(str(current))