## user:
def _build_admin_context(request, customer):
        """
        Build common admin context.
        """
        opts = customer._meta
        codename = get_permission_codename('change', opts)
        has_change_permission = request.user.has_perm('%s.%s' % (opts.app_label, codename))
        return {
            'has_change_permission': has_change_permission,
            'opts': opts
        }
for this code, your task is to make it runnable without bugs  give me the full code where the main function calling this function
## assistant:
In order to make the given code runnable, we need to create a simple Django project with the required classes, methods, and application setup. This example assumes you have Python and Django installed on your system. Here's the complete set of steps to create the main function calling the provided function:

1. Make sure you've installed Django:

```
pip install django
```

2. Create a new Django project and app:

```
django-admin startproject myproject
cd myproject
django-admin startapp myapp
```

3. Define your Customer model in `myapp/models.py`:

```python
from django.db import models

class Customer(models.Model):
    name = models.CharField(max_length=100)
```

4. Define your view function in `myapp/views.py`:

```python
from django.contrib.auth.views import login_required
from django.http import JsonResponse
from myapp.models import Customer
from django.contrib.contenttypes.models import ContentType
from django.contrib.auth.decorators import user_passes_test

def get_permission_codename(action, opts):
    return f"{action}_{opts.model_name}"

@login_required
@user_passes_test(lambda u: u.is_staff)
def build_admin_context(request, customer_id):
    """
    Build common admin context.
    """
    customer = Customer.objects.get(pk=customer_id)
    opts = customer._meta
    codename = get_permission_codename('change', opts)
    has_change_permission = request.user.has_perm('%s.%s' % (opts.app_label, codename))

    return JsonResponse({
        'has_change_permission': has_change_permission,
        'opts': {
            'app_label': opts.app_label,
            'model_name': opts.model_name,
        }
    })
```

5. Add the new view to `myapp/urls.py`:

```python
from django.urls import path
from . import views

urlpatterns = [
    path('admin_context/<int:customer_id>/', views.build_admin_context, name='build_admin_context')
]
```

6. Include the app's URLs in the project's `myproject/urls.py`:

```python
from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('myapp/', include('myapp.urls')),
]
```

7. Start the Django development server:

```
python manage.py runserver
```

Now you can navigate to `http://localhost:8000/myapp/admin_context/CUSTOMER_ID/` to check the `build_admin_context` view function with the main function calling it.
## user:
then write a class to test every line of the code you just generated
## assistant:
To test the code, you can use Django's built-in testing mechanism. Create a new file named `tests.py` in the `myapp` folder, and add the following code to it:

```python
from django.test import RequestFactory, TestCase
from django.contrib.auth.models import User, Permission
from myapp.models import Customer
from myapp.views import build_admin_context
from myapp.views import get_permission_codename

class BuildAdminContextTest(TestCase):
    def setUp(self):
        self.factory = RequestFactory()
        self.user = User.objects.create_user(
            username='testuser', password='testpassword'
        )

        self.staff_user = User.objects.create_user(
            username='staffuser', password='testpassword', is_staff=True
        )

        self.superuser = User.objects.create_superuser(
            username='superuser', password='testpassword'
        )

        self.customer = Customer.objects.create(name='Test Customer')

    def test_get_permission_codename(self):
        opts = self.customer._meta
        codename = get_permission_codename('change', opts)
        self.assertEqual(codename, 'change_customer')

    def test_build_admin_context(self):
        request = self.factory.get(f'/myapp/admin_context/{self.customer.pk}/')

        # Test with a non-authenticated user
        response = build_admin_context(request, self.customer.pk)
        self.assertEqual(response.status_code, 302) # Redirects to login

        # Authenticate the user
        request.user = self.user
        response = build_admin_context(request, self.customer.pk)
        self.assertEqual(response.status_code, 403) # Forbidden for non-staff
        
        # Test with staff user
        request.user = self.staff_user
        response = build_admin_context(request, self.customer.pk)
        self.assertEqual(response.status_code, 200)
        data = response.json()
        self.assertFalse(data['has_change_permission'])
        
        # Give the staff user change_customer permission
        change_permission = Permission.objects.get(codename='change_customer')
        self.staff_user.user_permissions.add(change_permission)

        # Test with staff user having the change_customer permission
        response = build_admin_context(request, self.customer.pk)
        self.assertEqual(response.status_code, 200)
        data = response.json()
        self.assertTrue(data['has_change_permission'])

        # Test with superuser
        request.user = self.superuser
        response = build_admin_context(request, self.customer.pk)
        self.assertEqual(response.status_code, 200)
        data = response.json()
        self.assertTrue(data['has_change_permission'])
```

This class will test the `get_permission_codename` function, the authentication system, and the `build_admin_context` function for different types of users (normal user, staff user, and superuser).

To run these tests, execute the following command in your terminal:

```
python manage.py test myapp.tests
```

It will run the tests and display the test results.
