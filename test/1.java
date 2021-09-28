public class Main {
    public static void main(String[] args) {
 
        // ����һǧ��β���
        int times = 10000000;
        // ��һ�����м���
        int firstCount = 0;
        // �ڶ������м���
        int secondCount = 0;
        while (times-- > 0) {
            // ��һ��ѡ��ĺ��ӵ�ַ��������������ѡ��
            int firstSelectedIndex = -1;
            // ������ѡ��ĺ��ӵ�ַ����δ��ѡ�������������ѡ��һ����
            int hostSelectedIndex = -1;
            // �ڶ���ѡ��ĺ��ӵ�ַ���ڱ�������ȥ���������������ѡ��
            int secondSelectedIndex = -1;
            // �������飬����������ӣ���ʼֵĬ��Ϊfalse�����ޱ���
            boolean[] boxes = new boolean[3];
            // ȡ�������һ��������Ϊtrue�����б���
            boxes[(int) (Math.random() * 3)] = true;
            // ���������������ѡ��һ�����ӣ���Ϊ��һ��ѡ��
            firstSelectedIndex = (int) (Math.random() * 3);
            // ���ڼ�¼������һ��δ��ѡ��ĺ��ӵĵ�ַ������
            int[] firstNotSelected = {-1, -1};
            // ��¼������һ��δ��ѡ��ĺ��ӵĵ�ַ
            for (int i = 0, j = 0; i < boxes.length; i++) {
                if (i != firstSelectedIndex) {
                    firstNotSelected[j] = i;
                    j++;
                }
            }
            // ��������һ��δ��ѡ��ĺ�������һ���պ�����Ϊ������ѡ��ĺ���
            for (int i = 0; i < firstNotSelected.length; i++) {
                if (!boxes[firstNotSelected[i]]) {
                    hostSelectedIndex = firstNotSelected[i];
                    break;
                }
            }
            // ���ڼ�¼����������ûѡ�Ŀպеĵ�ַ������
            int[] hostNotSelectedIndex = {-1, -1};
            // ��¼����������ûѡ�Ŀպеĵ�ַ
            for (int i = 0, j = 0; i < boxes.length; i++) {
                if (i != hostSelectedIndex) {
                    hostNotSelectedIndex[j] = i;
                    j++;
                }
            }
            // ѡ��ڶ���
            secondSelectedIndex = hostNotSelectedIndex[(int) (Math.random() * 2)];
            // �ڶ��β��ܺ͵�һ����ͬ�����ı�ѡ��
            while (secondSelectedIndex == firstSelectedIndex) {
                secondSelectedIndex = hostNotSelectedIndex[(int) (Math.random() * 2)];
            }
            // �ж������������
            if (boxes[firstSelectedIndex])
                firstCount++;
            if (boxes[secondSelectedIndex])
                secondCount++;
        }
        // ��ʾ�������
        System.out.println("������" + (firstCount + secondCount) + "��");
        System.out.println("��һ�Σ�δ�ı�ѡ�񣩲���" + firstCount + "��");
        System.out.println("�ڶ��Σ��ı�ѡ��  ����" + secondCount + "��");
    }
}