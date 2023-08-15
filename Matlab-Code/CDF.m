
c = {[0,18,25]/255,[9,147,150]/255,[238,155,0]/255,[174 32 18]/255};
figure
plot(line1(:,1),line1(:,2),'-','Color',c{1},'LineWidth',1.2)
hold on
plot(line2(:,1),line2(:,2),'-.','Color',c{4},'LineWidth',1.2)
ylim([0 1])
yticks(0:0.2:0.8)
xlim([0 0.45])
fun_set_axis_size('Estimation Error','CDF',16,[420 300]);
grid on
legend('\alpha=0.1, \beta=0.1','\alpha=0.15, \beta=0.15','Location','southeast')