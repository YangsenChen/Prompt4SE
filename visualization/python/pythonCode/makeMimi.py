def makeMimi(upid):
    """From http://cdn37.atwikiimg.com/sitescript/pub/dksitescript/FC2.site.js
    Also com.hps.util.fc2.FC2EncrptUtil.makeMimiLocal
    L110"""
    strSeed = "gGddgPfeaf_gzyr"
    prehash = upid + "_" + strSeed
    return md5(prehash.encode('utf-8')).hexdigest()

