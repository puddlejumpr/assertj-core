/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2019 the original author or authors.
 */
package org.assertj.core.util.temporal;

import java.time.Instant;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Comparator;

public class DefaultZonedDateTimeComparator extends AbstractByInstantComparator<ChronoZonedDateTime> {

  private static final Comparator<ChronoZonedDateTime> INSTANCE = new DefaultZonedDateTimeComparator();

  public static Comparator<ChronoZonedDateTime> getInstance() {
    return INSTANCE;
  }

  private DefaultZonedDateTimeComparator() {
    super();
  }

  @Override
  protected Instant getInstant(ChronoZonedDateTime temporal) {
    return temporal.toInstant();
  }

  @Override
  public String toString() {
    return "default ZonedDateTime comparison by instant";
  }
}
