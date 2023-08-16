close all
clear all
addpath('functions')
colors = {[0,18,25]/255,[9,147,150]/255,[238,155,0]/255,[174 32 18]/255};
%% The time cost data is within data\TimeCost, 
% We updata the code many times to make it stable in practice. 
% The result is better than what we used in the paper. The average time
% cost is about 2s if alpha = 0.1 and beta = 0.1.
path = 'data\TimeCost'
times1 = load([path '\300.txt']);
times2 = load([path '\400.txt']);
times3 = load([path '\600.txt']);
times =[mean(times1) mean(times2) mean(times3)]
figure
bar(times/1000)
xticklabels({'300' '400' '600'})
fun_set_axis_size('Number of Tags','Time Cost (s)',16,[420 300])
grid on


%% Below is the results we used in the paper.
figure
EC = [4.1 5 5.9 6.7 7.8 8.2]            
ATD = [2.6 2.1 2.2 2.3 2.4 2]
plot(500:100:1000,EC,'-.^','Color',colors{1},'LineWidth',1.2)
 hold on 
plot(500:100:1000,ATD,'-.s','Color',colors{4},'LineWidth',1.2)
ylim([0 10])
xlim([500 1000])
yticks(0:2:10)
% xticks([10 100 1000])
fun_set_axis_size('Number of Tags','Time Cost (s)',16,[420 300]);
legend('Exclusive collection','ATD','Location','northwest')
grid on