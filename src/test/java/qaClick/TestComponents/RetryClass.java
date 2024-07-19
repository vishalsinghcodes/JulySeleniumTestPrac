package qaClick.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryClass implements IRetryAnalyzer{

	int count =0;
	int maxCount = 2;
	
	@Override
	public boolean retry(ITestResult result) {
		
		if(count<maxCount) {
			count++;
			return true;
		}
		
		return false;
	}
}
