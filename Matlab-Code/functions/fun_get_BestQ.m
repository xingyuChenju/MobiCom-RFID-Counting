function [T2]= fun_get_M(alpha,beta,n)
%FUN_GET_M 此处显示有关此函数的摘要
%   此处显示详细说明
Mmin =  100000000000000000000000;
Tmin = 100000000000000000000000;
Qmin = 0;
for Q=5:14
    f=2^Q;
    if f<=n*4
        c = -norminv((beta)/2,0,1); 
        vt = fun_get_VT(f,n);
        p = fun_get_P(f,n);
        m =ceil(c^2*p^2*vt/(alpha^2*n^2));
        et= fun_get_ET(f,n);
        T = m*et;
        if T<Tmin
            Tmin = T;
            Mmin = m;
            Qmin = Q;
            q1 = n/f*(1-1/f)^(n-1);
            T2 = Mmin/q1*0.3+1;
        end
    else
        Mmin;
        T2 = Mmin/q1*0.4+1;
        break
    end 
end
Mmin;
end

function p = fun_get_P(f,n)
T = [1, 3.85, 1.2529];
t0 = T(1);
t2 = T(3);
p = 1/(- ((t0 - t2)*(f - 1))/n^2 - (f*t2*(1 - 1/f)^(1 - n))/n^2 - (f*t2*log(1 - 1/f)*(1 - 1/f)^(1 - n))/n);
end

function [vt] = fun_get_VT(f,n)
% T0 T1 T2待定
T = [1, 3.85, 1.2529];
a0 = (1-1/f)^n;
a1 = n/f*(1-1/f)^(n-1);
a2 = 1 - a1 - a0;
es = a0*T(1)+a1*T(2)+a2*T(3);
vt = es^2*(1-a1)/(a1^2);
end
