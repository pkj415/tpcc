package com.oltpbenchmark.api;

import org.HdrHistogram.ConcurrentHistogram;
import org.HdrHistogram.Histogram;

public class InstrumentedSQLStmt {
    final Histogram histogram;
    final SQLStmt sqlStmt;

    public static final int numSigDigits = 4;

    public InstrumentedSQLStmt(Histogram histogram, SQLStmt sqlStmt) {
        this.histogram = histogram;
        this.sqlStmt = sqlStmt;
    }
    public InstrumentedSQLStmt(Histogram histogram, String sql, int...substitutions) {
        this(histogram, new SQLStmt(sql, substitutions));
    }
    public InstrumentedSQLStmt(String sql, int...substitutions) {
        this(new ConcurrentHistogram(numSigDigits), sql, substitutions);
    }

    public SQLStmt getSqlStmt() {
        return sqlStmt;
    }

    public Histogram getHistogram() {
        return histogram;
    }

    public String getStats() {
       return getOperationLatencyString(histogram);
   }
 
   public static String getOperationLatencyString(Histogram histogram) {
       double avgLatency = histogram.getMean() / 1000;
       double p99Latency = histogram.getValueAtPercentile(99.0) / 1000;
       double p1Latency = histogram.getValueAtPercentile(1.0) / 1000;
       double std_dev = histogram.getStdDeviation() / 1000;
 
       return "Count : " + histogram.getTotalCount() + " P1 latency: " + p1Latency + " Avg Latency: " + avgLatency +
               " msecs, p99 Latency: " + p99Latency + " msecs, std_dev of latency: " +
               std_dev + " msecs";
  }
}
