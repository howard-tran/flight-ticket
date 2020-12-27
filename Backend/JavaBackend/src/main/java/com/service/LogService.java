package com.service;

import com.helper.IConsumer;
import com.helper.IFunction;
import com.helper.LogUtils;
import java.util.Optional;

public interface LogService<T> {
  public default Optional<T> runOptional(IFunction<T> func) {
    try {
      return Optional.of((T) func.run());
      //
    } catch (Exception e) {
      RuntimeException exception = new RuntimeException(e);
      LogUtils.LogError("[ERROR]", exception);

      return null;
    }
  }

  public default T run(IFunction<T> func) {
    try {
      return (T)func.run();
      //
    } catch (Exception e) {
      RuntimeException exception = new RuntimeException(e);
      LogUtils.LogError("[ERROR]", exception);

      return null;
    }
  }

  public default void testRun(IConsumer func) {
    try {
      func.run();
      //
    } catch (Exception e) {
      RuntimeException exception = new RuntimeException(e);
      LogUtils.LogError("[ERROR]", exception);
    }
  }
}
