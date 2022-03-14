# MULTIMODULE PROJECT SAMPLE

This project uses two modules, being one them an internal dependency. The 
modules are organized as:

- **Gateway** - Receives requests throught an web API;
- **Validator** - Uses a Brazilian algorithm to verify the ITIN number 
  **`aka CPF or Cadastro de Pessoa Física`**.

## INPUT

Both values below are accepted and uses regex to verify the pattern 
consistence before of send input number to **`validator`**. On validator the 
input value must be a length of eleven characters:

- **`XXX.XXX.XXX-XX`**
- **`XXXXXXXXXXX`**

## OUTPUT

The output uses a boolean value that indicates if value was approved or not, 
being very simple.

## CALCULUS

The calculus uses two steps based on sent number:

- **`X1 X2 X3 . X4 X5 X6 . X7 X8 X9 - X10 X11`**

Step one uses **`10`** to **`2`** descendent values:

- **`(10 * X1) + (9 * X2) + (... * Xn) + (2 * X9)`**

The total of products sum is divided by **`11`**:

- **`RES = TOTAL_OF_SUM/11`**

After that, uses only the decimal part to calculate the module:

- **`PRODUCT = RES(DECIMAL_FRAGMENT) * 11`**

Subtract **`TOTAL_OF_SUM`** from **`PRODUCT`** resulting on **`VD`**:

- **`VD = TOTAL_OF_SUM - PRODUCT`**

Step two uses **`11`** to **`2`** descendent values, but, the last value is the 
**`verification digit VD`** obtained on step above:

- **`(11 * X1) + (10 * X2) + (... * Xn) + (3 * X9) + (2 * VD)`**

All process is repeated here.

> p.s: When `VD` equals `0` or `1`, it holds `0`, by other side, `11` is 
> used to subtract from.