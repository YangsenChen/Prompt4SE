from code_06 import *

import unittest


class TestOperations(unittest.TestCase):
    def setUp(self):
        self.op = Operations()

    def test_set_fee_asset_with_amount(self):
        fee_asset = self.op.Amount("1.3.1")
        self.op.set_fee_asset(fee_asset)
        self.assertEqual(self.op.fee_asset_id, "1.3.1")

    def test_set_fee_asset_with_asset(self):
        fee_asset = self.op.Asset("1.3.2")
        self.op.set_fee_asset(fee_asset)
        self.assertEqual(self.op.fee_asset_id, "1.3.2")

    def test_set_fee_asset_with_string(self):
        fee_asset = "1.3.3"
        self.op.set_fee_asset(fee_asset)
        self.assertEqual(self.op.fee_asset_id, "1.3.3")

    def test_set_fee_asset_with_none(self):
        self.op.set_fee_asset(None)
        self.assertEqual(self.op.fee_asset_id, "1.3.0")


if __name__ == "__main__":
    unittest.main()