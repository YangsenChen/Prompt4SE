def isBin(self, type):
    '''
        is the type a byte array value?

        :param type: PKCS#11 type like `CKA_MODULUS`
        :rtype: bool
        '''
    return (not (self.isBool(type)) or \
        not (self.isString(type)) or \
        not (self.isNum(type)))