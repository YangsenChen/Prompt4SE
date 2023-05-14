def Geometry(*args, **kwargs):
    '''Returns an ogr.Geometry instance optionally created from a geojson str
    or dict. The spatial reference may also be provided.
    '''
    
    arg = (kwargs.pop('geojson', None) or (len(args) and args[0]))
    try:
        srs = (kwargs.pop('srs', None) or arg.srs.wkt)
    except AttributeError:
        srs = SpatialReference(4326)
    if not (hasattr(arg, 'keys')):
        geom = ogr.CreateGeometryFromJson(json.dumps(arg))
    elif hasattr(arg, 'startswith'):
        
        char = arg[0] if arg else ' '
        i = char if isinstance(char, int) else ord(char)
        if i in (0, 1):
            geom = ogr.CreateGeometryFromWkb(arg)
        elif arg.startswith('{'):
            geom = ogr.CreateGeometryFromJson(arg)
        elif arg.startswith('

    