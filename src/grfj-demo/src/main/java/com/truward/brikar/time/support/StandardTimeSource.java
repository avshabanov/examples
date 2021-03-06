package com.truward.brikar.time.support;

import com.truward.brikar.time.TimeSource;

import java.util.concurrent.TimeUnit;

/**
 * Implementation of {@link com.truward.brikar.time.TimeSource} that
 * uses {@link System#currentTimeMillis()} in order to retrieve current time.
 *
 * <p>
 * The returned time is the difference, measured in milliseconds, between the
 * current time and midnight, January 1, 1970 UTC.
 * </p>
 *
 * @author Alexander Shabanov
 */
public final class StandardTimeSource implements TimeSource {
  public static final TimeSource INSTANCE = new StandardTimeSource();

  private StandardTimeSource() {
  }

  @Override
  public long currentTime() {
    return System.currentTimeMillis();
  }

  @Override
  public TimeUnit getTimeUnit() {
    return TimeUnit.MILLISECONDS;
  }
}
