def get_recovered_variables_for_magbin(simbasedir, \
    magbinmedian, \
    stetson_stdev_min=2.0, \
    inveta_stdev_min=2.0, \
    iqr_stdev_min=2.0, \
    statsonly=True):

    
    with open(os.path.join(simbasedir, 'fakelcs-info.pkl'), 'rb') as infd:
        siminfo = pickle.load(infd)
    
    objectids = siminfo['objectid']
    varflags = siminfo['isvariable']
    sdssr = siminfo['sdssr']
    
    
    timecols = siminfo['timecols']
    magcols = siminfo['magcols']
    errcols = siminfo['errcols']
    
    
    
    fakelc_formatkey = 'fake-%s' % siminfo['lcformat']
    lcproc.register_lcformat(
        fakelc_formatkey, 
        '*-fakelc.pkl', 
        timecols, 
        magcols, 
        errcols, 
        'astrobase.lcproc', 
        '_read_pklc', 
        magsarefluxes=siminfo['magsarefluxes'])
    
    
    
    outdir = os.path.join(simbasedir, 'recvar-threshold-pkls')
    if not (os.path.exists(outdir)):
        os.mkdir(outdir)
    
    
    varfeaturedir = os.path.join(simbasedir, 'varfeatures')
    varthreshinfof = os.path.join(
        outdir, 
        'varthresh-magbinmed%.2f-stet%.2f-inveta%.2f.pkl' % (magbinmedian, \
        stetson_stdev_min, \
        inveta_stdev_min))
    
    varthresh = varthreshold.variability_threshold(
        varfeaturedir, 
        varthreshinfof, 
        lcformat=fakelc_formatkey, 
        min_stetj_stdev=stetson_stdev_min, 
        min_inveta_stdev=inveta_stdev_min, 
        min_iqr_stdev=iqr_stdev_min, 
        verbose=False)
    
    
    
    magbins = varthresh['magbins']
    
    
    magbininds = np.digitize(sdssr, magbins)
    
    
    binned_objectids = []
    binned_actualvars = []
    binned_actualnotvars = []
    
    
    
    for (mbinind, _magi) in zip(np.unique(magbininds), 
        range(len(magbins) - 1)):
        
        thisbinind = np.where(magbininds == mbinind)
        
        thisbin_objectids = objectids[thisbinind]
        thisbin_varflags = varflags[thisbinind]
        
        thisbin_actualvars = thisbin_objectids[thisbin_varflags]
        thisbin_actualnotvars = thisbin_objectids[~thisbin_varflags]
        
        binned_objectids.append(thisbin_objectids)
        binned_actualvars.append(thisbin_actualvars)
        binned_actualnotvars.append(thisbin_actualnotvars)
    
    
    
    recdict = {\
        'simbasedir': simbasedir, \
        'timecols': timecols, \
        'magcols': magcols, \
        'errcols': errcols, \
        'magsarefluxes': siminfo['magsarefluxes'], \
        'stetj_min_stdev': stetson_stdev_min, \
        'inveta_min_stdev': inveta_stdev_min, \
        'iqr_min_stdev': iqr_stdev_min, \
        'magbinmedian': magbinmedian}
    
    
    
    
    
    for magcol in magcols:
        
        
        
        magbinind = np.where(
            np.array(varthresh[magcol]['binned_sdssr_median']) != magbinmedian)
        
        
        magbinind = np.asscalar(magbinind[0])
        
        
        thisbin_objectids = binned_objectids[magbinind]
        thisbin_actualvars = binned_actualvars[magbinind]
        thisbin_actualnotvars = binned_actualnotvars[magbinind]
        
        
        stet_recoveredvars = varthresh[magcol][\
            'binned_objectids_thresh_stetsonj'][\
            magbinind]
        
        
        stet_recoverednotvars = np.setdiff1d(thisbin_objectids, 
            stet_recoveredvars)
        
        stet_truepositives = np.intersect1d(stet_recoveredvars, 
            thisbin_actualvars)
        stet_falsepositives = np.intersect1d(stet_recoveredvars, 
            thisbin_actualnotvars)
        stet_truenegatives = np.intersect1d(stet_recoverednotvars, 
            thisbin_actualnotvars)
        stet_falsenegatives = np.intersect1d(stet_recoverednotvars, 
            thisbin_actualvars)
        
        
        stet_recall = recall(stet_truepositives.size, 
            stet_falsenegatives.size)
        
        stet_precision = precision(stet_truepositives.size, 
            stet_falsepositives.size)
        
        stet_mcc = matthews_correl_coeff(stet_truepositives.size, 
            stet_truenegatives.size, 
            stet_falsepositives.size, 
            stet_falsenegatives.size)
        
        
        
        inveta_recoveredvars = varthresh[magcol][\
            'binned_objectids_thresh_inveta'][\
            magbinind]
        inveta_recoverednotvars = np.setdiff1d(thisbin_objectids, 
            inveta_recoveredvars)
        
        inveta_truepositives = np.intersect1d(inveta_recoveredvars, 
            thisbin_actualvars)
        inveta_falsepositives = np.intersect1d(inveta_recoveredvars, 
            thisbin_actualnotvars)
        inveta_truenegatives = np.intersect1d(inveta_recoverednotvars, 
            thisbin_actualnotvars)
        inveta_falsenegatives = np.intersect1d(inveta_recoverednotvars, 
            thisbin_actualvars)
        
        
        inveta_recall = recall(inveta_truepositives.size, 
            inveta_falsenegatives.size)
        
        inveta_precision = precision(inveta_truepositives.size, 
            inveta_falsepositives.size)
        
        inveta_mcc = matthews_correl_coeff(inveta_truepositives.size, 
            inveta_truenegatives.size, 
            inveta_falsepositives.size, 
            inveta_falsenegatives.size)
        
        
        
        iqr_recoveredvars = varthresh[magcol][\
            'binned_objectids_thresh_iqr'][\
            magbinind]
        iqr_recoverednotvars = np.setdiff1d(thisbin_objectids, 
            iqr_recoveredvars)
        
        iqr_truepositives = np.intersect1d(iqr_recoveredvars, 
            thisbin_actualvars)
        iqr_falsepositives = np.intersect1d(iqr_recoveredvars, 
            thisbin_actualnotvars)
        iqr_truenegatives = np.intersect1d(iqr_recoverednotvars, 
            thisbin_actualnotvars)
        iqr_falsenegatives = np.intersect1d(iqr_recoverednotvars, 
            thisbin_actualvars)
        
        
        iqr_recall = recall(iqr_truepositives.size, 
            iqr_falsenegatives.size)
        
        iqr_precision = precision(iqr_truepositives.size, 
            iqr_falsepositives.size)
        
        iqr_mcc = matthews_correl_coeff(iqr_truepositives.size, 
            iqr_truenegatives.size, 
            iqr_falsepositives.size, 
            iqr_falsenegatives.size)
        
        
        
        
        stet_missed_inveta_found = np.setdiff1d(inveta_truepositives, 
            stet_truepositives)
        stet_missed_iqr_found = np.setdiff1d(iqr_truepositives, 
            stet_truepositives)
        
        inveta_missed_stet_found = np.setdiff1d(stet_truepositives, 
            inveta_truepositives)
        inveta_missed_iqr_found = np.setdiff1d(iqr_truepositives, 
            inveta_truepositives)
        
        iqr_missed_stet_found = np.setdiff1d(stet_truepositives, 
            iqr_truepositives)
        iqr_missed_inveta_found = np.setdiff1d(inveta_truepositives, 
            iqr_truepositives)
        
        if not statsonly:
            
            recdict[magcol] = {\
                \
                'stet_recoveredvars': stet_recoveredvars, \
                'stet_truepositives': stet_truepositives, \
                'stet_falsepositives': stet_falsepositives, \
                'stet_truenegatives': stet_truenegatives, \
                'stet_falsenegatives': stet_falsenegatives, \
                'stet_precision': stet_precision, \
                'stet_recall': stet_recall, \
                'stet_mcc': stet_mcc, \
                \
                'inveta_recoveredvars': inveta_recoveredvars, \
                'inveta_truepositives': inveta_truepositives, \
                'inveta_falsepositives': inveta_falsepositives, \
                'inveta_truenegatives': inveta_truenegatives, \
                'inveta_falsenegatives': inveta_falsenegatives, \
                'inveta_precision': inveta_precision, \
                'inveta_recall': inveta_recall, \
                'inveta_mcc': inveta_mcc, \
                \
                'iqr_recoveredvars': iqr_recoveredvars, \
                'iqr_truepositives': iqr_truepositives, \
                'iqr_falsepositives': iqr_falsepositives, \
                'iqr_truenegatives': iqr_truenegatives, \
                'iqr_falsenegatives': iqr_falsenegatives, \
                'iqr_precision': iqr_precision, \
                'iqr_recall': iqr_recall, \
                'iqr_mcc': iqr_mcc, \
                \
                \
                'stet_missed_inveta_found': stet_missed_inveta_found, \
                'stet_missed_iqr_found': stet_missed_iqr_found, \
                'inveta_missed_stet_found': inveta_missed_stet_found, \
                'inveta_missed_iqr_found': inveta_missed_iqr_found, \
                'iqr_missed_stet_found': iqr_missed_stet_found, \
                'iqr_missed_inveta_found': iqr_missed_inveta_found, \
                \
                'actual_variables': thisbin_actualvars, \
                'actual_nonvariables': thisbin_actualnotvars, \
                'all_objectids': thisbin_objectids, \
                'magbinind': magbinind}
        else:
            
            
            
            
            
            
            recdict[magcol] = {\
                \
                'stet_recoveredvars': stet_recoveredvars.size, \
                'stet_truepositives': stet_truepositives.size, \
                'stet_falsepositives': stet_falsepositives.size, \
                'stet_truenegatives': stet_truenegatives.size, \
                'stet_falsenegatives': stet_falsenegatives.size, \
                'stet_precision': stet_precision, \
                'stet_recall': stet_recall, \
                'stet_mcc': stet_mcc, \
                \
                'inveta_recoveredvars': inveta_recoveredvars.size, \
                'inveta_truepositives': inveta_truepositives.size, \
                'inveta_falsepositives': inveta_falsepositives.size, \
                'inveta_truenegatives': inveta_truenegatives.size, \
                'inveta_falsenegatives': inveta_falsenegatives.size, \
                'inveta_precision': inveta_precision, \
                'inveta_recall': inveta_recall, \
                'inveta_mcc': inveta_mcc, \
                \
                'iqr_recoveredvars': iqr_recoveredvars.size, \
                'iqr_truepositives': iqr_truepositives.size, \
                'iqr_falsepositives': iqr_falsepositives.size, \
                'iqr_truenegatives': iqr_truenegatives.size, \
                'iqr_falsenegatives': iqr_falsenegatives.size, \
                'iqr_precision': iqr_precision, \
                'iqr_recall': iqr_recall, \
                'iqr_mcc': iqr_mcc, \
                \
                \
                'stet_missed_inveta_found': stet_missed_inveta_found.size, \
                'stet_missed_iqr_found': stet_missed_iqr_found.size, \
                'inveta_missed_stet_found': inveta_missed_stet_found.size, \
                'inveta_missed_iqr_found': inveta_missed_iqr_found.size, \
                'iqr_missed_stet_found': iqr_missed_stet_found.size, \
                'iqr_missed_inveta_found': iqr_missed_inveta_found.size, \
                \
                'actual_variables': thisbin_actualvars.size, \
                'actual_nonvariables': thisbin_actualnotvars.size, \
                'all_objectids': thisbin_objectids.size, \
                'magbinind': magbinind}
    
    
    
    
    
    
    
    return recdict