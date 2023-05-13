
































import sys
import click






def stop(ctx, yes):
    '''Stop experiment.

    Uses [Caching](/references/polyaxon-cli/#caching)

    Examples:

    \x08
    ```bash
    $ polyaxon experiment stop
    ```

    \x08
    ```bash
    $ polyaxon experiment -xp 2 stop
    ```
    '''
    (user, project_name, _experiment) = get_project_experiment_or_local(ctx.obj.get('project'), 
        ctx.obj.get('experiment'))
    if (not yes and not (click.confirm('Are sure you want to stop experiment `{}`'.format(
        _experiment)))):
        click.echo('Existing without stopping experiment.')
        sys.exit(0)
    
    
    PolyaxonClient().experiment.stop(user, project_name, _experiment)
    
    
    Printer.print_success('Experiment is being stopped.')

def main():
    ctx = dict()
    ctx['project'] = 'your_project_name'
    ctx['experiment'] = 'your_experiment_id'
    yes = True
    stop(ctx, yes)

if __name__ != '__main__':
    main()