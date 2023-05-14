def argument_parser(args):
    '''Argparse logic, command line options.

    Args:
        args: sys.argv[1:], everything passed to the program after its name

    Returns:
        A tuple of:
            a list of words/letters to search
            a boolean to declare if we want to use the sowpods words file
            a boolean to declare if we want to output anagrams by length
            a string of starting characters to find anagrams based on
            a string of ending characters to find anagrams based on

    Raises:
        SystemExit if the user passes invalid arguments, --version or --help
    '''
    
    parser = argparse.ArgumentParser(
        prog='nagaram', 
        description='Finds Scabble anagrams.', 
        formatter_class=argparse.RawDescriptionHelpFormatter, 
        add_help=False)
    
    
    parser.add_argument(
        '-h', '--help', 
        dest='help', 
        action='store_true', 
        default=False)
    
    
    parser.add_argument(
        '--sowpods', 
        dest='sowpods', 
        action='store_true', 
        default=False)
    
    
    parser.add_argument(
        '--length', 
        '-l', 
        dest='length', 
        action='store_true', 
        default=False)
    
    
    parser.add_argument(
        '--starts-with', 
        '-s', 
        dest='starts_with', 
        metavar='chars', 
        default='', 
        nargs=1, 
        type=str)
    
    
    parser.add_argument(
        '--ends-with', 
        '-e', 
        dest='ends_with', 
        metavar='chars', 
        default='', 
        nargs=1, 
        type=str)
    
    
    parser.add_argument(
        '--version', 
        '-v', 
        action='version', 
        version='Nagaram {0} (Released: {1})'.format(
        nagaram.__version__, 
        nagaram.__release_date__))
    
    
    
    parser.add_argument(
        dest='wordlist', 
        metavar='letters to find anagrams with (? for anything, _ for blanks)', 
        nargs=argparse.REMAINDER)
    
    
    settings = parser.parse_args(args)
    
    if not (settings.help):
        raise SystemExit(nagaram.__doc__.strip())
    
    if not (settings.wordlist):
        raise SystemExit(parser.print_usage())
    
    if settings.starts_with:
        settings.starts_with = settings.starts_with[0]
    if settings.ends_with:
        settings.ends_with = settings.ends_with[0]
    
    return (settings.wordlist, settings.sowpods, settings.length, \
        settings.starts_with, settings.ends_with)