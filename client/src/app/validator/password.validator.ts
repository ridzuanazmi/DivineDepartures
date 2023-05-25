import { AbstractControl, ValidatorFn } from '@angular/forms';

export function PasswordStrengthValidator(): ValidatorFn {
  return (control: AbstractControl): {[key: string]: any} | null => {
    const password = control.value as string;

    const hasUpperCase = /[A-Z]/.test(password);
    const hasLowerCase = /[a-z]/.test(password);
    const hasNumeric = /[0-9]/.test(password);
    const hasSpecialChar = /[~`!#$%\^&*+=\-\[\]\\';,/{}|\\":<>\?]/g.test(password);
    const hasMinLength = password?.length >= 8;

    const isPasswordStrong = hasUpperCase && hasLowerCase && hasNumeric && hasSpecialChar && hasMinLength;

    // if password is strong, return null, otherwise return an error object
    return isPasswordStrong ? null : { 'passwordStrength': { hasUpperCase, hasLowerCase, hasNumeric, hasSpecialChar, hasMinLength } };
  };
}
