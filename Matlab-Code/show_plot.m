close all
clear all
n = 10000;
f = 1000:100:20000;
sum_VR = zeros(length(f),1);
for index =1:length(f)
    for w = 1:50
        sum_VR(index) = sum_VR(index)+ fun_demo(f(index),n,w);
    end
end
[v,index] = max(sum_VR);

figure
plot(f,sum_VR)
