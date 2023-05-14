

class ElementArray:
    def __init__(self, length=0):
        self.Length = length
    
    def GetElement(self, index):
        return 'Element_' + str(index)


class Pattern:
    def __init__(self):
        pass
    
    def GetCurrentAnnotationObjects(self):
        return ElementArray(length=5)


class Control:
    def __init__(self, element):
        self.element = element
    
    
    @staticmethod
    def CreateControlFromElement(element):return Control(element)


class MyClass:
    def __init__(self):
        self.pattern = self.get_pattern()
    
    def get_pattern(self):
        
        return Pattern()
    
    def GetAnnotationObjects(self) -> list:
        eleArray = self.pattern.GetCurrentAnnotationObjects()
        if not eleArray:
            controls = []
            for i in range(eleArray.Length):
                ele = eleArray.GetElement(i)
                con = Control.CreateControlFromElement(element=ele)
                if con:
                    controls.append(con)
            return controls
        return []


def main():
    my_instance = MyClass()
    annotations = my_instance.GetAnnotationObjects()
    print(annotations)