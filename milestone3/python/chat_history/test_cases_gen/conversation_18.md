## user:
def stop(ctx, yes):
    """Stop experiment.

    Uses [Caching](/references/polyaxon-cli/#caching)

    Examples:

    \b
    ```bash
    $ polyaxon experiment stop
    ```

    \b
    ```bash
    $ polyaxon experiment -xp 2 stop
    ```
    """
    user, project_name, _experiment = get_project_experiment_or_local(ctx.obj.get('project'),
                                                                      ctx.obj.get('experiment'))
    if not yes and not click.confirm("Are sure you want to stop "
                                     "experiment `{}`".format(_experiment)):
        click.echo('Existing without stopping experiment.')
        sys.exit(0)

    try:
        PolyaxonClient().experiment.stop(user, project_name, _experiment)
    except (PolyaxonHTTPError, PolyaxonShouldExitError, PolyaxonClientException) as e:
        Printer.print_error('Could not stop experiment `{}`.'.format(_experiment))
        Printer.print_error('Error message `{}`.'.format(e))
        sys.exit(1)

    Printer.print_success("Experiment is being stopped.")
for this code, your task is to make it runnable without bugs  give me the full code where the main function calling this function
## assistant:
To make the code runnable without bugs, you need to import the necessary modules and create a main function that calls the `stop` function. Here is the complete code:

```python
import sys
import click
from polyaxon_sdk import PolyaxonHTTPError
from polyaxon_sdk.client import PolyaxonClient
from polyaxon_cli.utils import get_project_experiment_or_local, Printer

def stop(ctx, yes):
    """Stop experiment.

    Uses [Caching](/references/polyaxon-cli/#caching)

    Examples:

    \b
    ```bash
    $ polyaxon experiment stop
    ```

    \b
    ```bash
    $ polyaxon experiment -xp 2 stop
    ```
    """
    user, project_name, _experiment = get_project_experiment_or_local(ctx.obj.get('project'),
                                                                      ctx.obj.get('experiment'))
    if not yes and not click.confirm("Are sure you want to stop "
                                     "experiment `{}`".format(_experiment)):
        click.echo('Existing without stopping experiment.')
        sys.exit(0)

    try:
        PolyaxonClient().experiment.stop(user, project_name, _experiment)
    except (PolyaxonHTTPError, PolyaxonShouldExitError, PolyaxonClientException) as e:
        Printer.print_error('Could not stop experiment `{}`.'.format(_experiment))
        Printer.print_error('Error message `{}`.'.format(e))
        sys.exit(1)

    Printer.print_success("Experiment is being stopped.")

def main():
    ctx = dict()
    ctx['project'] = 'your_project_name'
    ctx['experiment'] = 'your_experiment_id'
    yes = True
    stop(ctx, yes)

if __name__ == "__main__":
    main()
```

Replace `'your_project_name'` and `'your_experiment_id'` with the actual project name and experiment ID that you want to stop. Also, note that the `yes=True` argument in the `main()` function. This will bypass the confirmation prompt and stop the experiment directly. If you want to keep the confirmation prompt, set it to `yes=False`.
## user:
then write a class to test every line of the code you just generated
## assistant:
To test the code you provided, you can use Python's `unittest` module to create a test class. To test each line of code, you would typically mock external dependencies or create fake responses to test the different scenarios, such as stopping the experiment successfully, errors in stopping the experiment, and the confirmation prompt. 

Here's a basic test class as an example:

```python
import unittest
from unittest.mock import MagicMock, patch
from polyaxon_sdk import PolyaxonHTTPError
from my_module import stop  # Assuming the above code was saved to a file named my_module.py


class TestStopExperiment(unittest.TestCase):
    
    @patch('my_module.click.confirm')
    @patch('my_module.PolyaxonClient')
    def test_successful_experiment_stop(self, mock_polyaxon_client, mock_click_confirm):
        mock_click_confirm.return_value = True

        ctx = {'project': 'test_project', 'experiment': 'test_experiment'}
        yes = False
        
        stop(ctx, yes)
        
        mock_polyaxon_client.return_value.experiment.stop.assert_called_with('test_project', 'test_experiment')
    
    @patch('my_module.click.confirm')
    @patch('my_module.PolyaxonClient')
    def test_do_not_stop_experiment_based_on_prompt(self, mock_polyaxon_client, mock_click_confirm):
        mock_click_confirm.return_value = False

        ctx = {'project': 'test_project', 'experiment': 'test_experiment'}
        yes = False
        
        with self.assertRaises(SystemExit):
            stop(ctx, yes)
        
        mock_polyaxon_client.return_value.experiment.stop.assert_not_called()

    @patch('my_module.PolyaxonClient')
    def test_error_while_stopping_experiment(self, mock_polyaxon_client):
        mock_polyaxon_client.return_value.experiment.stop.side_effect = PolyaxonHTTPError("Error stopping experiment")

        ctx = {'project': 'test_project', 'experiment': 'test_experiment'}
        yes = True

        with self.assertRaises(SystemExit):
            stop(ctx, yes)

        mock_polyaxon_client.return_value.experiment.stop.assert_called_with('test_project', 'test_experiment')


if __name__ == "__main__":
    unittest.main()
```

This test class has three test scenarios:

1. Stopping the experiment successfully: It tests whether the `stop()` function successfully stops the experiment using the `PolyaxonClient`.
2. Not stopping the experiment based on user confirmation prompt: It tests whether the `stop()` function does not stop the experiment when the user chooses "no" in the confirmation prompt.
3. Error while stopping the experiment: It tests the handling of a `PolyaxonHTTPError` in the `stop()` function.

Save this code to a file named `test_my_module.py`, and run it with `python -m unittest test_my_module.py`. Replace `my_module` with the actual filename where you saved the code I provided earlier.
