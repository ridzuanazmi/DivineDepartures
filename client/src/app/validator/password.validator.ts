import { AbstractControl, ValidatorFn } from '@angular/forms';

export function PasswordStrengthValidator(): ValidatorFn {
  return (control: AbstractControl): {[key: string]: any} | null => {
    const password = control.value as string;
    let error = null;

    if(password) {
      const hasUpperCase = /[A-Z]/.test(password);
      const hasLowerCase = /[a-z]/.test(password);
      const hasNumeric = /[0-9]/.test(password);
      const hasSpecialChar = /[~`!#$%\^&*+=\-\[\]\\';,/{}|\\":<>\?]/g.test(password);
      const hasMinLength = password.length >= 8;

      const isPasswordStrong = hasUpperCase && hasLowerCase && hasNumeric && hasMinLength && hasSpecialChar;
      // if password is not strong, return an error object
      if (!isPasswordStrong) {
        error = { 'passwordStrength': { hasUpperCase, hasLowerCase, hasNumeric, hasMinLength, hasSpecialChar } };
      }
    } else {
      // password is empty
      error = { 'required': true };
    }

    return error;
  };
}
