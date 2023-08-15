close all
clear all

syms t0 t1 t2 n f
h(t0,t1,t2,f,n) = fun_get_vt(t0,t1,t2,f,n);
diff(h(t0,t1,t2,f,n),n)

function [vt] = fun_get_vt(t0,t1,t2,f,n)
% T0 T1 T2´ý¶¨
q0 = (1-1/f)^n;
q1 = n/f*(1-1/f)^(n-1);
q2 = 1 - q0 - q1;
vt =  (t0-t2)*(f-1)/n+t2/q1;
end



