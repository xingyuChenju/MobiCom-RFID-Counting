function [vt] = fun_get_vt(f,n)
% T0 T1 T2´ý¶¨
a0 = (1-1/f)^n;
a1 = n/f*(1-1/f)^(n-1);
a2 = 1 - a1 - a1;
vt = 0;
for w = 1:50
vt = vt+(a0*a1*a2*(1-a1)^(w-3))/w;
end
end

