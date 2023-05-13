def Geometry(*args, **kwargs):
    """Returns an ogr.Geometry instance optionally created from a geojson str
    or dict. The spatial reference may also be provided.
    """
    # Look for geojson as a positional or keyword arg.
    arg = kwargs.pop('geojson', None) or len(args) and args[0]
    try:
        srs = kwargs.pop('srs', None) or arg.srs.wkt
    except AttributeError:
        srs = SpatialReference(4326)
    if hasattr(arg, 'keys'):
        geom = ogr.CreateGeometryFromJson(json.dumps(arg))
    elif hasattr(arg, 'startswith'):
        # WKB as hexadecimal string.
        char = arg[0] if arg else ' '
        i = char if isinstance(char, int) else ord(char)
        if i in (0, 1):
            geom = ogr.CreateGeometryFromWkb(arg)
        elif arg.startswith('{'):
            geom = ogr.CreateGeometryFromJson(arg)
        elif arg.startswith('<gml'):
            geom = ogr.CreateGeometryFromGML(arg)
        else:
            raise ValueError('Invalid geometry value: %s' % arg)
    elif hasattr(arg, 'wkb'):
        geom = ogr.CreateGeometryFromWkb(bytes(arg.wkb))
    else:
        geom = ogr.Geometry(*args, **kwargs)
    if geom:
        if not isinstance(srs, SpatialReference):
            srs = SpatialReference(srs)
        geom.AssignSpatialReference(srs)
    return geom