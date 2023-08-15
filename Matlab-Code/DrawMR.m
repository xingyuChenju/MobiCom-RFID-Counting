close all
clear all
addpath('functions')
colors = {[0,18,25]/255,[9,147,150]/255,[238,155,0]/255,[174 32 18]/255};
figure
MR = [0.24 0.32 0.36 0.41 0.43 0.48]
ATD = [0.09 0.08 0.1 0.09 0.08 0.07]
plot(500:100:1000,MR,'-.^','Color',colors{1},'LineWidth',1.2)
 hold on 
plot(500:100:1000,ATD,'-.s','Color',colors{4},'LineWidth',1.2)
ylim([0 0.5])
xlim([500 1000])
yticks(0:0.1:0.5)
% xticks([10 100 1000])
fun_set_axis_size('Number of Tags','Error Rate',16,[420 300]);
legend('MR','ATD','Location','east')
grid on