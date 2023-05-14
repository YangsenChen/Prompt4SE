# def stop(ctx, yes):
#     """Stop experiment.
#
#     Uses [Caching](/references/polyaxon-cli/#caching)
#
#     Examples:
#
#     \b
#     ```bash
#     $ polyaxon experiment stop
#     ```
#
#     \b
#     ```bash
#     $ polyaxon experiment -xp 2 stop
#     ```
#     """
#     user, project_name, _experiment = get_project_experiment_or_local(ctx.obj.get('project'),
#                                                                       ctx.obj.get('experiment'))
#     if not yes and not click.confirm("Are sure you want to stop "
#                                      "experiment `{}`".format(_experiment)):
#         click.echo('Existing without stopping experiment.')
#         sys.exit(0)
#
#     try:
#         PolyaxonClient().experiment.stop(user, project_name, _experiment)
#     except (PolyaxonHTTPError, PolyaxonShouldExitError, PolyaxonClientException) as e:
#         Printer.print_error('Could not stop experiment `{}`.'.format(_experiment))
#         Printer.print_error('Error message `{}`.'.format(e))
#         sys.exit(1)
#
#     Printer.print_success("Experiment is being stopped.")

import sys
import click
# from polyaxon_sdk import PolyaxonHTTPError
# from polyaxon_sdk.client import PolyaxonClient
# from polyaxon_cli.utils import get_project_experiment_or_local, Printer



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


    PolyaxonClient().experiment.stop(user, project_name, _experiment)


    Printer.print_success("Experiment is being stopped.")

def main():
    ctx = dict()
    ctx['project'] = 'your_project_name'
    ctx['experiment'] = 'your_experiment_id'
    yes = True
    stop(ctx, yes)

if __name__ == "__main__":
    main()