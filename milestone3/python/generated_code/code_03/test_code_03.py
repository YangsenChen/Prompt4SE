import unittest

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
        """Registers a new metric to this context"""
        collector = self.get_metrics_collector()
        collector.register_metric(name, metric, time_bucket_in_sec)


class TestMetrics(unittest.TestCase):
    def setUp(self):
        self.context = Context()

    def test_register_metric(self):
        self.context.register_metric("test_metric", 100, 60)
        metric = self.context.get_metrics_collector().metrics["test_metric"]
        self.assertEqual(metric.name, "test_metric")
        self.assertEqual(metric.value, 100)
        self.assertEqual(metric.time_bucket_in_sec, 60)

    def test_get_metrics_collector(self):
        collector = self.context.get_metrics_collector()
        self.assertIsInstance(collector, MetricCollector)


if __name__ == "__main__":
    unittest.main()
