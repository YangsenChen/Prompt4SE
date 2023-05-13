def isBin(self, type):
    '''
        is the type a byte array value?

        :param type: PKCS#11 type like `CKA_MODULUS`
        :rtype: bool
        '''
    return (not (self.isBool(type)) and \
        self.isString(type) and \
        not (self.isNum(type)))