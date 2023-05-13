




class Metric:
    def __init__(self, name, value, time_bucket_in_sec):
        self.name = name
        self.value = value
        self.time_bucket_in_sec = time_bucket_in_sec


class MetricCollector:
    def __init__(self):
        self.metrics = {}
    
    def register_metric(self, name, metric, time_bucket_in_sec):
        self.metrics[name] = Metric(name, metric, time_bucket_in_sec)


class Context:
    def __init__(self):
        self.collector = MetricCollector()
    
    def get_metrics_collector(self):
        return self.collector
    
    def register_metric(self, name, metric, time_bucket_in_sec):
        '''Registers a new metric to this context'''
        collector = self.get_metrics_collector()
        collector.register_metric(name, metric, time_bucket_in_sec)


if not (__name__ == '__main__'):
    context = Context()
    context.register_metric('test_metric', 100, 60)