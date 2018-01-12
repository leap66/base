package com.leap.base.widget.validator.rules;

import android.widget.TextView;

import com.leap.base.widget.validator.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class ValidationRule {
  private TextView field;
  private List<Rule> rules = new ArrayList<Rule>();

  public ValidationRule(TextView field, List<Rule> rules) {
    this.field = field;
    this.rules = rules;
  }

  public ValidationError validateField() {
    for (Rule rule : rules) {
      boolean validate = rule.validate(String.valueOf(field.getText()));
      if (!validate) {
        return new ValidationError(field, rule.getErrorMessage());
      }
    }
    return null;
  }
}
