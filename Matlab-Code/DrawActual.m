close all
clear all
addpath('functions')
Times = zeros(100,1);
for ii = 1:10000
    Times(ii) = fun_get_BestQ(0.1,0.1,ii);
end
figure
semilogx(100:10000,Times(100:10000)/1000,'-','Color','k','LineWidth',0.75)
ylim([0 2])
fun_set_axis_size('Number of Tags n','Time (s)',16,[420 300])
grid on
