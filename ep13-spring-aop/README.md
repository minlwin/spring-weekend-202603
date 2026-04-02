# Writing PointCut

## execution(method pattern)

| Category | Test Pattern | Point Cut Expression |
| -------- | ------------ | -------------------- | 
| Return Type   | Any Return Type   | `execution(* com.jdc..*.*(..))`  |
|  | Void Return Type   | `execution(void com.jdc..*.*(..))`  |
|  | Specific Return Type   | `execution(int com.jdc..*.*(..))`  |
| Class Name | Start With   | `execution(* com.jdc..Payment*.*(..))`  |
|  | End With   | `execution(* com.jdc..*Provider.*(..))`  |
| Method Name | Start With   | `execution(* com.jdc..*.paid*(..))`  |
|  | End With   | `execution(* com.jdc..*.*Callback(..))`  |
| Arguments | Any Number of Params   | `execution(* com.jdc..*.*(..))`  |
|  | No Param   | `execution(* com.jdc..*.*())`  |
|  | Any One Param   | `execution(* com.jdc..*.*(*))`  |
|  | One Specific Param   | `execution(* com.jdc..*.*(int))`  |
|  | Any Two Params   | `execution(* com.jdc..*.*(*, *))`  |
|  | Two Specific Params   | `execution(* com.jdc..*.*(String, int))`  |
