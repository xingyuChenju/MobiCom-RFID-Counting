function [ET] = fun_get_ET(f,n)
%FUN_GET_ET
T = [1, 3.85, 1.2529];
q0 = (1-1/f)^n;
q1 = n/f*(1-1/f)^(n-1);
q2 = 1 - q1 - q0;
ET = (T(1)*q0+T(2)*q1+T(3)*q2)/q1;
end

