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
 * Copyright 2012-2022 the original author or authors.
 */
package org.assertj.core.internal.classes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.ShouldBePrimitive.shouldNotBePrimitive;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.util.AssertionsUtil.expectAssertionError;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.ClassesBaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for
 * <code>{@link org.assertj.core.internal.Classes#assertIsNotPrimitive(AssertionInfo, Class)}</code> .
 *
 * @author Manuel Gutierrez
 */
class Classes_assertIsNotPrimitive_Test extends ClassesBaseTest {

  @Test
  void should_pass_if_actual_is_an_object() {
    // GIVEN
    actual = Object.class;
    // WHEN/THEN
    classes.assertIsNotPrimitive(someInfo(), actual);
  }

  @Test
  void should_fail_if_actual_is_null() {
    // GIVEN
    actual = null;
    // WHEN
    AssertionError assertionError = expectAssertionError(() -> assertThat(actual).isNotPrimitive());
    // THEN
    then(assertionError).hasMessage(actualIsNull());
  }

  @ParameterizedTest
  @ValueSource(classes = { byte.class, short.class, int.class, long.class, float.class, double.class, boolean.class,
    char.class })
  void should_fail_if_actual_is_a_primitive_type(Class<?> primitiveClass) {
    // WHEN
    AssertionError assertionError = expectAssertionError(() -> assertThat(primitiveClass).isNotPrimitive());
    // THEN
    then(assertionError).hasMessage(shouldNotBePrimitive(primitiveClass).create());
  }

}
