# def create(cls, data, id_=None):
#         """Create a deposit.
#
#         Initialize the follow information inside the deposit:
#
#         .. code-block:: python
#
#             deposit['_deposit'] = {
#                 'id': pid_value,
#                 'status': 'draft',
#                 'owners': [user_id],
#                 'created_by': user_id,
#             }
#
#         The deposit index is updated.
#
#         :param data: Input dictionary to fill the deposit.
#         :param id_: Default uuid for the deposit.
#         :returns: The new created deposit.
#         """
#         data.setdefault('$schema', current_jsonschemas.path_to_url(
#             current_app.config['DEPOSIT_DEFAULT_JSONSCHEMA']
#         ))
#         if '_deposit' not in data:
#             id_ = id_ or uuid.uuid4()
#             cls.deposit_minter(id_, data)
#
#         data['_deposit'].setdefault('owners', list())
#         if current_user and current_user.is_authenticated:
#             creator_id = int(current_user.get_id())
#
#             if creator_id not in data['_deposit']['owners']:
#                 data['_deposit']['owners'].append(creator_id)
#
#             data['_deposit']['created_by'] = creator_id
#
#         return super(Deposit, cls).create(data, id_=id_)

import uuid
from flask import current_app, Flask
from flask_security import Security, SQLAlchemyUserDatastore, UserMixin, RoleMixin, current_user
from flask_sqlalchemy import SQLAlchemy
from flask_jsonschema import JsonSchema, ValidationError

# Initial configuration
app = Flask(__name__)
app.config["SECRET_KEY"] = "this-is-a-secret-key"
app.config["SQLALCHEMY_DATABASE_URI"] = "sqlite:////tmp/test.db"
app.config["DEPOSIT_DEFAULT_JSONSCHEMA"] = "deposit_deposit-v1.0.0.json"

db = SQLAlchemy(app)
jsonschema = JsonSchema(app)

# Define the User and Role model for the flask-security library
roles_users = db.Table("roles_users",
                       db.Column("user_id", db.Integer(), db.ForeignKey("user.id")),
                       db.Column("role_id", db.Integer(), db.ForeignKey("role.id")))

class Role(db.Model, RoleMixin):
    id = db.Column(db.Integer(), primary_key=True)
    name = db.Column(db.String(80), unique=True)
    description = db.Column(db.String(255))

class User(db.Model, UserMixin):
    id = db.Column(db.Integer, primary_key=True)
    email = db.Column(db.String(255), unique=True)
    password = db.Column(db.String(255))
    active = db.Column(db.Boolean())
    roles = db.relationship("Role", secondary=roles_users, backref=db.backref("users", lazy="dynamic"))

# Setup Flask-Security
user_datastore = SQLAlchemyUserDatastore(db, User, Role)
security = Security(app, user_datastore)

class Deposit():
    @staticmethod
    def deposit_minter(id_, data):
        deposit_data = {
            'id': id_,
            'status': 'draft',
            'owners': [0],
            'created_by': None,
        }
        data["_deposit"] = deposit_data

    @classmethod
    def create(cls, data, id_=None):
        """Create a deposit instance."""
        data.setdefault('$schema', jsonschema.path_to_url(
            current_app.config['DEPOSIT_DEFAULT_JSONSCHEMA']
        ))
        if '_deposit' not in data:
            id_ = id_ or uuid.uuid4()
            cls.deposit_minter(id_, data)

        data['_deposit'].setdefault('owners', list())
        if current_user and current_user.is_authenticated:
            creator_id = int(current_user.get_id())

            if creator_id not in data['_deposit']['owners']:
                data['_deposit']['owners'].append(creator_id)

            data['_deposit']['created_by'] = creator_id

        return cls(data, id_=id_)

    def __init__(self, data, id_):
        self.data = data
        self.id_ = id_


# Main function
def main():
    # Set initial data for the deposit
    data = {
        "title": "My Test Deposit",
        "description": "A description of the test deposit",
    }

    # Create the deposit
    deposit = Deposit.create(data)

    # Print results
    import pprint
    pretty_print = pprint.PrettyPrinter(indent=4)
    pretty_print.pprint(deposit.data) if deposit.data else None

if __name__ == "__main__":
    main()