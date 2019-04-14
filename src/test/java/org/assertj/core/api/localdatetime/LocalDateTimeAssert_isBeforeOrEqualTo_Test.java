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
package org.assertj.core.api.localdatetime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.error.ShouldBeBeforeOrEqualTo.shouldBeBeforeOrEqualTo;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import java.time.LocalDateTime;

import org.assertj.core.internal.ChronoLocalDateTimeComparator;
import org.assertj.core.internal.ComparatorBasedComparisonStrategy;
import org.junit.jupiter.api.Test;

/**
 * @author Paweł Stawicki
 * @author Joel Costigliola
 * @author Marcin Zajączkowski
 */
public class LocalDateTimeAssert_isBeforeOrEqualTo_Test extends LocalDateTimeAssertBaseTest {

  @Test
  public void test_isBeforeOrEqual_assertion() {
    // WHEN
    assertThat(BEFORE).isBeforeOrEqualTo(REFERENCE);
    assertThat(REFERENCE).isBeforeOrEqualTo(REFERENCE);
    // THEN
    verify_that_isBeforeOrEqual_assertion_fails_and_throws_AssertionError(AFTER, REFERENCE);
  }

  @Test
  public void test_isBeforeOrEqual_assertion_error_message() {
    ChronoLocalDateTimeComparator comparator = ChronoLocalDateTimeComparator.getInstance();
    ComparatorBasedComparisonStrategy strategy = new ComparatorBasedComparisonStrategy(comparator, comparator.description());
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> assertThat(REFERENCE).isBeforeOrEqualTo(BEFORE))
                                                   .withMessage(shouldBeBeforeOrEqualTo(REFERENCE, BEFORE, strategy).create());
  }

  @Test
  public void should_fail_if_actual_is_null() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
      LocalDateTime actual = null;
      assertThat(actual).isBeforeOrEqualTo(LocalDateTime.now());
    }).withMessage(actualIsNull());
  }

  @Test
  public void should_fail_if_dateTime_parameter_is_null() {
    assertThatIllegalArgumentException().isThrownBy(() -> assertThat(LocalDateTime.now()).isBeforeOrEqualTo((LocalDateTime) null))
                                        .withMessage("The LocalDateTime to compare actual with should not be null");
  }

  @Test
  public void should_fail_if_dateTime_as_string_parameter_is_null() {
    assertThatIllegalArgumentException().isThrownBy(() -> assertThat(LocalDateTime.now()).isBeforeOrEqualTo((String) null))
                                        .withMessage("The String representing the LocalDateTime to compare actual with should not be null");
  }

  private static void verify_that_isBeforeOrEqual_assertion_fails_and_throws_AssertionError(LocalDateTime dateToCheck,
                                                                                            LocalDateTime reference) {
    assertThatThrownBy(() -> assertThat(dateToCheck).isBeforeOrEqualTo(reference)).isInstanceOf(AssertionError.class);
    assertThatThrownBy(() -> assertThat(dateToCheck).isBeforeOrEqualTo(reference.toString())).isInstanceOf(AssertionError.class);
  }

}
