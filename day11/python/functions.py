
def readParseInput(filePath):
    with open(filePath, "r") as file:
        content = file.read()
        return [int(num) for num in content.split(' ')]