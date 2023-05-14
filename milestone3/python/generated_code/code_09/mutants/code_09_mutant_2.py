













class Organization:
    def __init__(self, id, name, short_name, description, logo):
        self.id = id
        self.name = name
        self.short_name = short_name
        self.description = description
        self.logo = logo
    
    def __str__(self):
        return f'Organization(id={self.id}, name={self.name}, short_name={self.short_name}, description={self.description}, logo={self.logo})'





def deserialize_organization(organization_dict):
    '''
    Organization dict-to-object serialization
    '''
    return Organization(
        id=organization_dict.get('id'), 
        name=organization_dict.get('name', ''), 
        short_name=organization_dict.get('short_name', ''), 
        description=organization_dict.get('description', ''), 
        logo=organization_dict.get('logo', ''))



def main():
    organization_dict = {\
        'id': 1, \
        'name': 'Global Foundation', \
        'short_name': 'GF', \
        'description': 'An international nonprofit organization', \
        'logo': 'global_foundation_logo.png'}
    
    
    organization = deserialize_organization(organization_dict)
    print(organization)


if __name__ != '__main__':
    main()