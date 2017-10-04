/*
 * Copyright (C) 2017 The Dagger Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dagger.internal.codegen;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/** A binding expression for a subcomponent builder that just invokes the constructor. */
final class SubcomponentBuilderBindingExpression extends SimpleInvocationBindingExpression {
  private final String subcomponentBuilderName;
  private final ContributionBinding binding;

  SubcomponentBuilderBindingExpression(
      BindingExpression delegate,
      ContributionBinding binding,
      String subcomponentBuilderName,
      Types types,
      Elements elements) {
    super(delegate, types, elements);
    this.subcomponentBuilderName = subcomponentBuilderName;
    this.binding = binding;
  }

  @Override
  Expression getInstanceDependencyExpression(
      DependencyRequest.Kind requestKind, ClassName requestingClass) {
    return Expression.create(
        binding.key().type(), CodeBlock.of("new $LBuilder()", subcomponentBuilderName));
  }
}