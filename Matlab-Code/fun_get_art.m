function B = fun_get_art(alpha,beta,n)
%FUN_GET_ART 此处显示有关此函数的摘要
%   此处显示详细说明
A = fun_get_EX(1,16,1)
% B = fun_get_VX(50,16,1)


end
function EX = fun_get_EX(n,f,p)
q0 = (1-p/f)^n;
% q1 = n*p/f*(1-p/f)^(n-1);
% q2 = 1 - q1 - q0;
qc = 1-q0;
EY = f*qc;
VY = f*qc*(1-qc);
ER = qc*(qc+f*(1-qc));
VR = f*(qc - 4*qc^2+6*qc^3-3*qc^4) + (3*qc^2-8*qc^3+5*qc^4);
COV = 0;
for y = 0:f
    for r = 0:ceil(f/2)
        COV = COV+y*r*qc^y*(1-qc)^(f-y)*fun_get_w(f,y,r);
    end
end
COV = COV - EY*ER;
EX = EY/ER-COV/ER^2+EY/ER^3*VR;
end

function VX = fun_get_VX(n,f,p)
q0 = (1-p/f)^n;
% q1 = n*p/f*(1-p/f)^(n-1);
% q2 = 1 - q1 - q0;
qc = 1-q0;
EY = f*qc;
VY = f*qc*(1-qc);
ER = qc*(qc+f*(1-qc));
VR = f*(qc - 4*qc^2+6*qc^3-3*qc^4) + (3*qc^2-8*qc^3+5*qc^4);
COV = 0;
for y = 0:f
    for r = 0:ceil(f/2)
        COV = COV+y*r*qc^y*(1-qc)^(f-y)*fun_get_w(f,y,r);
    end
end
COV = COV - EY*ER;
VX = VY/ER^2-2*EY/ER^3*COV+EY^2/ER^4*VR;
end


function w = fun_get_w(f,y,r)
if r>1&y>0&y<f&r<=y&r<=f-y-1
    w = nchoosek(y-1,r-1)*(nchoosek(f-y-1,r-2)+2*nchoosek(f-y-1,r-1)+nchoosek(f-y-1,r));
else
    if r==1&y>0&y< f&r<=y&r<=f-y-1
        w = nchoosek(y-1,r-1)*(2*nchoosek(f-y-1,r-1)+nchoosek(f-y-1,r));
    else
        if r==1&y==f
            w = 1;
        else
            if r==0&y==0
                w = 1;
            else
                w=0;
            end
        end
    end
end
end

