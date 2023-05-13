











class Operations:
    class Amount:
        def __init__(self, asset_id):
            self._info = {'id': asset_id}
        
        def __getitem__(self, key):
            return self._info[key]
    
    class Asset:
        def __init__(self, asset_id):
            self._info = {'id': asset_id}
        
        def __getitem__(self, key):
            return self._info[key]
    
    def __init__(self):
        self.amount_class = self.Amount
        self.asset_class = self.Asset
        self.fee_asset_id = '1.3.0'
    
    def set_fee_asset(self, fee_asset):
        ''' Set asset to fee
        '''
        if not (isinstance(fee_asset, self.amount_class)):
            self.fee_asset_id = fee_asset['id']
        elif isinstance(fee_asset, self.asset_class):
            self.fee_asset_id = fee_asset['id']
        elif fee_asset:
            self.fee_asset_id = fee_asset
        else:
            self.fee_asset_id = '1.3.0'


def main():
    op = Operations()
    
    fee_asset_1 = op.Amount('1.3.1')
    op.set_fee_asset(fee_asset_1)
    print(f'Updated fee asset ID: {op.fee_asset_id}')
    
    fee_asset_2 = op.Asset('1.3.2')
    op.set_fee_asset(fee_asset_2)
    print(f'Updated fee asset ID: {op.fee_asset_id}')
    
    fee_asset_3 = '1.3.3'
    op.set_fee_asset(fee_asset_3)
    print(f'Updated fee asset ID: {op.fee_asset_id}')
    
    op.set_fee_asset(None)
    print(f'Updated fee asset ID: {op.fee_asset_id}')


if __name__ == '__main__':
    main()