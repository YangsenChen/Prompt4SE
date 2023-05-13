from code_09 import *

import unittest


class TestDeserializeOrganization(unittest.TestCase):

    def test_deserialize_organization_correct_instance(self):
        organization_dict = {
            'id': 1,
            'name': 'Global Foundation',
            'short_name': 'GF',
            'description': 'An international nonprofit organization',
            'logo': 'global_foundation_logo.png'
        }

        organization = deserialize_organization(organization_dict)

        self.assertIsInstance(organization, Organization)
        self.assertEqual(organization.id, 1)
        self.assertEqual(organization.name, 'Global Foundation')
        self.assertEqual(organization.short_name, 'GF')
        self.assertEqual(organization.description, 'An international nonprofit organization')
        self.assertEqual(organization.logo, 'global_foundation_logo.png')

    def test_deserialize_organization_default_values(self):
        organization_dict = {
            'id': 2
        }

        organization = deserialize_organization(organization_dict)

        self.assertEqual(organization.id, 2)
        self.assertEqual(organization.name, '')
        self.assertEqual(organization.short_name, '')
        self.assertEqual(organization.description, '')
        self.assertEqual(organization.logo, '')

    def test_organization_str_method(self):
        organization = Organization(id=1, name='Global Foundation', short_name='GF', description='An international nonprofit organization', logo='global_foundation_logo.png')
        expected_output = 'Organization(id=1, name=Global Foundation, short_name=GF, description=An international nonprofit organization, logo=global_foundation_logo.png)'
        self.assertEqual(expected_output, str(organization))


if __name__ == '__main__':
    unittest.main()